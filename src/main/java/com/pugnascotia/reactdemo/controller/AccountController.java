package com.pugnascotia.reactdemo.controller;

import javax.servlet.http.HttpServletRequest;

import com.pugnascotia.reactdemo.config.ajax.AjaxLogoutSuccessHandler;
import com.pugnascotia.reactdemo.utils.State;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import static com.pugnascotia.reactdemo.utils.State.populateModel;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Handles a request for the signin page and renders the
 * app. React Router takes care of showing the right page.
 */

@Controller
@Slf4j
public class AccountController {

	@RequestMapping("/signin")
	public String showSignIn(Model model, HttpServletRequest request) {
		populateModel(model, request);
		return "index";
	}
	/**
	 * Returns the user's current authentication status.
	 *
	 * @see AjaxLogoutSuccessHandler for how this is used.
	 */
	@RequestMapping(value="/api/account",produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String,Object> getAccountStatus(HttpServletRequest request) {
		return State.getAuthState(request);
	}
}
