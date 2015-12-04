package com.sgem.utilidades;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import urn.ebay.api.PayPalAPI.GetBalanceReq;
import urn.ebay.api.PayPalAPI.GetBalanceRequestType;
import urn.ebay.api.PayPalAPI.GetBalanceResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.ErrorType;

public class GetBalance {
	
	public GetBalanceResponseType getBalance() {

		Logger logger = Logger.getLogger(this.getClass().toString());

		// ## GetBalanceReq
		GetBalanceReq getBalanceReq = new GetBalanceReq();
		GetBalanceRequestType getBalanceRequest = new GetBalanceRequestType();

		// Indicates whether to return all currencies. It is one of the
		// following values:
		// 
		// * 0 – Return only the balance for the primary currency holding.
		// * 1 – Return the balance for each currency holding.
		getBalanceRequest.setReturnAllCurrencies("1");
		getBalanceReq.setGetBalanceRequest(getBalanceRequest);

		// ## Creating service wrapper object
		// Creating service wrapper object to make API call and loading
		// configuration file for your credentials and endpoint
		PayPalAPIInterfaceServiceService service = null;
		Map<String, String> customConfigurationMap = new HashMap<String, String>();
		customConfigurationMap.put("mode", "sandbox");
//		customConfigurationMap.put("acct1.UserName", "jb-us-seller_api1.paypal.com"); 
//		customConfigurationMap.put("acct1.Password", "WX4WTU3S8MY44S7F");
//		customConfigurationMap.put("acct1.Signature", "AFcWxV21C7fd0v3bYYYRCpSSRl31A7yDhhsPUU2XhtMoZXsWHFxu-RWy");
		customConfigurationMap.put("acct1.UserName", "manularra_1-facilitator_api1.hotmail.com"); 
		customConfigurationMap.put("acct1.Password", "LUHEFZZN7J6NXUU9");
		customConfigurationMap.put("acct1.Signature", "Agb881FQ.f8sP7QrETuXVaqhQ8QfA5S9XpWTMuncyNk60b1kQMdq-TuL");
		customConfigurationMap.put("service.EndPoint", "https://api-3t.sandbox.paypal.com/nvp");//https://api-3t.sandbox.paypal.com/2.0/ "https://api-3t.sandbox.paypal.com/nvp");
					
		service = new PayPalAPIInterfaceServiceService(customConfigurationMap);			//"src/sdk_config.properties");
		
		GetBalanceResponseType getBalanceResponse = null;
		try {
			// ## Making API call
			// Invoke the appropriate method corresponding to API in service
			// wrapper object
			 getBalanceResponse = service.getBalance(getBalanceReq);
		} catch (Exception e) {
			System.out.println("Error Message : " +e.getMessage());
		}
		// ## Accessing response parameters
		// You can access the response parameters using getter methods in
		// response object as shown below
		// ### Success values
		if (getBalanceResponse.getAck().getValue()
				.equalsIgnoreCase("success")) {

			Iterator<BasicAmountType> iterator = getBalanceResponse
					.getBalanceHoldings().iterator();
			while (iterator.hasNext()) {
				BasicAmountType amount = iterator.next();
				
				// Available balance and associated currency code for each currency held, including the primary currency. The first currency is the primary currency.
				logger.info("Balance Holdings : " + amount.getValue() + " "
						+ amount.getCurrencyID());
			}
		}
		// ### Error Values
		// Access error values from error list using getter methods
		else {
			List<ErrorType> errorList = getBalanceResponse.getErrors();
			System.out.println("API Error Message : " + errorList.get(0).getLongMessage());
		}
		return getBalanceResponse;
	}

}
