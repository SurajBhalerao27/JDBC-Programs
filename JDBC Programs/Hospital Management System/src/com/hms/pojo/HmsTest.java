package com.hms.pojo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class HmsTest {
	private static final String url = "jdbc:mysql://localhost:3306/hospital";
	private static final String username = "root";
	private static final String password = "root";

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			Scanner scanner = new Scanner(System.in);
			Patient patient = new Patient(connection, scanner);
			Doctor doctor = new Doctor(connection);

			while (true) {
				System.out.println("<-- HOSPITAL MANAGEMENT SYSTEM -->");
				System.out.println("1. Add Patient");
				System.out.println("2. View Patients");
				System.out.println("3. View Doctors");
				System.out.println("4. Book Appointment");
				System.out.println("5. Exit");

				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					patient.addPatient();
					System.out.println();
					break;
				case 2:
					patient.viewPatient();
					System.out.println();
					break;
				case 3:
					doctor.viewDoctor();
					System.out.println();
					break;
				case 4:
					Appointment.bookAppointment(patient, doctor, connection, scanner);
					System.out.println();
					break;
				case 5:
					System.out.println("Thank you for visit !");
					return;
				default:
					System.err.println("Enter Valid Choice!!!");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
