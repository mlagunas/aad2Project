/**
 * Class about the information shared (here, an account)
 * @author Alexandre
 *
 */

package com.example.aad2project.model;

import android.provider.BaseColumns;

public class SharedInformation {

	public SharedInformation() {
	}

	public static final class Account implements BaseColumns {

		//Information shared
		public Account(String id, String email, String password) {}

		public static final String ACCOUNT_ID = "ACCOUNT";
		public static final String ACCOUNT_EMAIL = "ACCOUNT_EMAIL";
		public static final String ACCOUNT_PASSWORD = "ACCOUNT_PASSWORD";
	}

}
