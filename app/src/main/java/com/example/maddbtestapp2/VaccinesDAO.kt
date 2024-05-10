package com.example.maddbtestapp2

import com.example.maddbtestapp2.vaccine.Vaccines
import java.sql.Date

interface VaccinesDAO {
    fun getVaccineById(id: Int): Vaccines?
    fun getVaccineByName(vaccineName: String): Vaccines?
    fun doesVaccineExist(vaccineName: String): Boolean
    fun getVaccineIdByVaccineName(vaccineName: String): Int
    fun getDateAdministeredByVaccineName(id: Int): Date?
    fun getAllVaccines(): Set<Vaccines?>?
    fun insertVaccine(vaccine: Vaccines) : Boolean
    fun updateVaccine(id: Int, vaccine: Vaccines) : Boolean
    fun deleteVaccine(id: Int) : Boolean
}