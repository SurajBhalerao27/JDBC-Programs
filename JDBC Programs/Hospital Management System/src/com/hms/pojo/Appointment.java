package com.hms.pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Appointment {
	public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner) {
		System.out.println("Enter Patient id: ");
		int patientId = scanner.nextInt();
		System.out.println("Enter Doctor id: ");
		int doctorId = scanner.nextInt();
		System.out.println("Enter Appointment date (YYYY-MM-DD): ");
		String date = scanner.next();

		if (patient.getPatientById(patientId) && doctor.getPatientById(doctorId)) {
			if (checkAvailibility(doctorId, date, connection)) {
				String appointmentQuery = "INSERT INTO APPOINTMENTS(patient_id,doctors_id, appointment_date) values (?,?,?)";
				try {
					PreparedStatement statement = connection.prepareStatement(appointmentQuery);
					statement.setInt(1, patientId);
					statement.setInt(2, doctorId);
					statement.setString(3, date);

					int executeUpdate = statement.executeUpdate();
					if (executeUpdate > 0) {
						System.out.println("Appointment Booked");
					} else {
						System.err.println("Failed to book");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Doctor not available at this date !");
			}
		} else {
			System.err.println("Either patient or doctor does not exist");
		}
	}

	private static boolean checkAvailibility(int doctorId, String date, Connection connection) {
		String query = "SELECT COUNT(*)FROM APPOINTMENTS WHERE doctor_id = ? AND appointment_id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, doctorId);
			statement.setString(2, date);
			ResultSet executeQuery = statement.executeQuery();
			if (executeQuery.next()) {
				int count = executeQuery.getInt(1);
				if (count == 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
