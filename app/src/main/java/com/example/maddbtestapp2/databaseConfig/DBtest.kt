//package com.example.maddbtestapp2.databaseConfig
//
//import java.sql.Date
//import java.sql.Time
//import com.example.maddbtestapp2.databaseConfig.dbConnect
//import com.example.maddbtestapp2.vaccine.Vaccines
//
//fun main() {
//    try {
//        // Getting connection using DBConnection class
//        val connection = dbConnect().getConnection()
//
////        val appointmentQuery = AppointmentsQueries(connection)
////        val patientQuery = PatientsQueries(connection)
////        val doctorQuery = DoctorsQueries(connection)
////        val vaccineQuery = VaccinesQueries(connection)
//
////        // test insert a patient
////        println("Testing insertPatient():")
////        val newPatient = Patients("12345678901", "a24fg57ghy18ifur72ht62trk9ly", "Daniel", "Dave", Date.valueOf("2001-11-02"))
////        println("Insertion successful: ${patientQuery.insertPatient(newPatient)}")
////
////        println("Testing getAllPatients():")
////        println(patientQuery.getAllPatients())
////
////        // test insert a doctor
////        println("Testing insertDoctor():")
////        val newDoctor = Doctors(1, "Michael", "Jordan")
////        println("Insertion successful: ${doctorQuery.insertDoctor(newDoctor)}")
////
////        println("Testing getAllDoctors():")
////        println(doctorQuery.getAllDoctors())
//
//        // test insert a vaccine
//        println("Testing insertVaccine():")
//        val newVaccine = Vaccines(1, "Moderna", 2, 14)
//        println("Insertion successful: ${vaccineQuery.insertVaccine(newVaccine)}")
//
//        println("Testing getAllVaccines():")
//        println(vaccineQuery.getAllVaccines())
//
////
////        // Testing methods
////        println("Testing insertAppointment():")
////        val newAppointment = Appointments(1, 1, "12345678901", 1, Date.valueOf("2024-04-12"), Time.valueOf("12:30:00"), "Street Street", 1)
////        println("Insertion successful: ${appointmentQuery.insertAppointment(newAppointment)}")
////
////        println("Testing getAllAppointments():")
////        println(appointmentQuery.getAllAppointments())
//
//        // Closing connection
//        connection.close()
//    } catch (e: Exception) {
//        e.printStackTrace() // Printing error information in case an exception occurs
//    }
////}