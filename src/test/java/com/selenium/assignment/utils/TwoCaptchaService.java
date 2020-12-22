package com.selenium.assignment.utils;

import java.io.IOException;

public class TwoCaptchaService {
	
	/**
	 * This class is used to establish a connection to 2captcha.com 
	 * and receive the token for solving google recaptcha v2
	 * 
	 * @author Chillivanilli
	 * @version 1.0
	 * 
	 * If you have a custom software requests, please contact me 
	 * via forum: http://thebot.net/members/chillivanilli.174861/
	 * via eMail: chillivanilli@chillibots.com
	 * via skype: ktlotzek
	 */
	
	
	/**
	 * Your 2captcha.com captcha KEY
	 */
	private String apiKey;
	
	
	/**
	 * The google site key from the page you want to solve the recaptcha at
	 */
	private String googleKey;
	
	
	/**
	 * The URL where the recaptcha is placed.
	 * For example: https://www.google.com/recaptcha/api2/demo
	 */
	private String pageUrl;
	
	/**
	 * The HttpWrapper which the requests are made with
	 */
	private HttpWrapper hw;
	
	
	/**
	 * Constructor if you don't use any proxy
	 * @param apiKey
	 * @param googleKey
	 * @param pageUrl
	 */
	public TwoCaptchaService(String apiKey, String googleKey, String pageUrl) {
		this.apiKey = apiKey;
		this.googleKey = googleKey;
		this.pageUrl = pageUrl;
		hw = new HttpWrapper();
	}

	
	/**
	 * Sends the recaptcha challenge to 2captcha.com and 
	 * checks every second if a worker has solved it
	 * 
	 * @return The response-token which is needed to solve and submit the recaptcha
	 * @throws InterruptedException, when thread.sleep is interrupted
	 * @throws IOException, when there is any server issue and the request cannot be completed
	 */
	public String solveCaptcha() throws InterruptedException, IOException {
		System.out.println("Sending recaptcha challenge to 2captcha.com");
		
		String parameters = "key=" + apiKey
				+ "&method=userrecaptcha"
				+ "&googlekey=" + googleKey
				+ "&pageurl=" + pageUrl;

		hw.get("http://2captcha.com/in.php?" + parameters);
		
		String captchaId = hw.getHtml().replaceAll("\\D", "");
		int timeCounter = 0;
		
		do {
			hw.get("http://2captcha.com/res.php?key=" + apiKey 
					+ "&action=get"
					+ "&id=" + captchaId);
			
			Thread.sleep(1000);
			
			timeCounter++;
			System.out.println("Waiting for captcha to be solved");
		} while(hw.getHtml().contains("NOT_READY"));
		
		System.out.println("It took "  + timeCounter + " seconds to solve the captcha");
		String gRecaptchaResponse = hw.getHtml().replaceAll("OK\\|", "").replaceAll("\\n", "");
		return gRecaptchaResponse;
	}

	/**
	 * 
	 * @return The 2captcha.com captcha key
	 */
	public String getApiKey() {
		return apiKey;
	}
	
	/**
	 * Sets the 2captcha.com captcha key
	 * @param apiKey
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	/**
	 * 
	 * @return The google site key
	 */
	public String getGoogleKey() {
		return googleKey;
	}
	
	/**
	 * Sets the google site key
	 * @param googleKey
	 */
	public void setGoogleKey(String googleKey) {
		this.googleKey = googleKey;
	}
	
	/**
	 *
	 * @return The page url
	 */
	public String getPageUrl() {
		return pageUrl;
	}
	
	/**
	 * Sets the page url
	 * @param pageUrl
	 */
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

}
