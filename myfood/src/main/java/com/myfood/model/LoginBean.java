package com.myfood.model;

public class LoginBean
{
		private String userName;

		private String password;

		public String getPassword()
		{
				return this.password;
		}

		public String getUserName()
		{
				return this.userName;
		}

		public void setUserName(String userName)
		{
				this.userName = userName;
		}

		public void setPassword(String password)
		{
				this.password = password;
		}

		public LoginBean(String username, String password) {
			super();
			this.userName = username;
			this.password = password;
		}

		public LoginBean() {
			super();
		}


}
