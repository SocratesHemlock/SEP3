﻿using System;
using T1Driver.Models;
using T1Driver.Services;

namespace T1Driver.Data
{
    public class UserServices:IUserServices
    {
        public void Connect()
        {
            ClientController.getInstance().Connect();
        }

        public string Register(string username, string password)
        {
            return ClientController.getInstance().Register(username,password);
        }

        public Driver Login(string username, string password)
        {
            return ClientController.getInstance().Login(username,password);
        }

        public void Logout(Driver driver)
        {
            ClientController.getInstance().Logout(driver);
        }

        public Driver GetDriver(string username)
        {
            return ClientController.getInstance().GetDriver(username);
        }

        public Driver EditDriver(int id, string username, string password, string firstName, string secondName,
            string birthday, string sex)
        {
            return ClientController.getInstance().EditDriver(id, username, password, firstName, secondName, birthday, sex);
        }

    }
}