package com.hms.pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
	private Connection connection;
	private Scanner scanner;

	public Patient(Connection connection, Scanner scanner) {
		super();
		this.connection = connection;
		this.scanner = scanner;
	}

// add
	public void addPatient() {
		System.out.println("Enter patient name: ");
		String name = scanner.next();

		System.out.println("Enter patient age: ");
		int age = scanner.nextInt();

		System.out.println("Enter patient gender: ");
		String gender = scanner.next();

		try {
			String query = "INSERT INTO PATIENTS(name,age,gender) VALUES (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setInt(2, age);
			statement.setString(3, gender);

			int executeUpdate = statement.executeUpdate();
			if (executeUpdate > 0) {
				System.out.println("Patient data added successfully !!!");
			} else {
				System.err.println("Error");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// view patient
	public void viewPatient() {
		String query = "SELECT * FROM PATIENTS";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet executeQuery = statement.executeQuery(query);
			System.out.println(" PATIENTS DATA:  ");
			System.out.println("+-------------+-------------------+--------+------------------+");
			System.out.println("| Patient_Id  | Name              | Age    | Gender           |");
			System.out.println("+-------------+-------------------+--------+------------------+");

			while (executeQuery.next()) {
				int id = executeQuery.getInt("id");
				String name = executeQuery.getString("name");
				int age = executeQuery.getInt("age");
				String gender = executeQuery.getString("gender");
				System.out.printf("| %-12s| %-18s| %-7s| %-17s|\n", id, name, age, gender);
				System.out.println("+-------------+-------------------+--------+------------------+");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// get patient
	public boolean getPatientById(int patientId) {
		String query = "SELECT * FROM PATIENTS WHERE ID=?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet executeQuery = statement.executeQuery();
			if (executeQuery.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
