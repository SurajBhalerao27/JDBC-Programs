package com.hms.pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
	private Connection connection;

	public Doctor(Connection connection) {
		super();
		this.connection = connection;
	}

// view patient
	public void viewDoctor() {
		String query = "SELECT * FROM DOCTORS";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet executeQuery = statement.executeQuery(query);
			System.out.println(" DOCTORS DATA:  ");
			System.out.println("+-------------+-------------------+-------------------------+");
			System.out.println("| Doctor_Id   | Name              | Specialization          |");
			System.out.println("+-------------+-------------------+-------------------------+");

			while (executeQuery.next()) {
				int id = executeQuery.getInt("id");
				String name = executeQuery.getString("name");
				String specialization = executeQuery.getString("specialization");
				System.out.printf("| %-12s| %-18s| %-24s|\n", id, name, specialization);
				System.out.println("+-------------+-------------------+-------------------------+");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// get patient
	public boolean getPatientById(int doctorId) {
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
