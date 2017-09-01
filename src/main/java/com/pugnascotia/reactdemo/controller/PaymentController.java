package com.pugnascotia.reactdemo.controller;

import javax.servlet.http.HttpServletRequest;

import com.pugnascotia.reactdemo.service.PaypalService;
import com.pugnascotia.reactdemo.utils.Paypal;
import com.pugnascotia.reactdemo.utils.URLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
@RequestMapping("/")
public class PaymentController {

	public static final String PAYPAL_SUCCESS_URL = "api/pay/success";
	public static final String PAYPAL_CANCEL_URL = "api/pay/cancel";

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PaypalService paypalService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(){
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST, value = "api/pay/request")
	public String pay(HttpServletRequest request,@RequestParam("total") Double total,@RequestParam("currency") String currency,@RequestParam("description")String description ){
		String cancelUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL;
		String successUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_SUCCESS_URL;
		try {
			Payment payment = paypalService.createPayment(
					total,
					currency,
					Paypal.PaypalPaymentMethod.paypal,
					Paypal.PaypalPaymentIntent.sale,
					description,
					cancelUrl,
					successUrl);
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_URL)
	public String cancelPay(){
		return "cancel";
	}

	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				return "success";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

}
