package com.qa.hippo.utilities;

import java.sql.*;

public class OTPFetcher {

//    // Database credentials
//    private static final String DB_URL = "jdbc:postgresql://ecomm-backend.chfh7l48r6z1.ap-south-1.rds.amazonaws.com:5432/hippoecomm";
//    private static final String USER = "niyaz_read";
//    private static final String PASS = "Test01";
    public static String fetchOTP(String mobileNumber) {
        String otp = null;
        String query = "SELECT otp FROM user_otps WHERE mobile = ? ";

        try (Connection connection = DriverManager.getConnection(ConfigLoader.get("DB_URL"), ConfigLoader.get("DB_USERNAME"), ConfigLoader.get("DB_PASSWORD"));
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, mobileNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                otp = resultSet.getString("otp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return otp;
    }

}
