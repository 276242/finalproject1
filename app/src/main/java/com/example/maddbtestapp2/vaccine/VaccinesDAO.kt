package com.example.maddbtestapp2.vaccine

import com.example.maddbtestapp2.vaccine.Vaccines
import java.sql.Date

/**
 * Interface for data access operations related to vaccines.
 *
 * @property getVaccineById Retrieves a vaccine by its id.
 * @property getVaccineByName Retrieves a vaccine by its name.
 * @property doesVaccineExist Checks if a vaccine exists by its name.
 * @property getVaccineIdByVaccineName Retrieves a vaccine id by its name.
 * @property getDateAdministeredByVaccineId Retrieves the date a vaccine was administered by its id.
 * @property getAllVaccines Retrieves all vaccines.
 * @property insertVaccine Inserts a new vaccine.
 * @property updateVaccine Updates an existing vaccine.
 * @property updateVaccineName Updates the name of an existing vaccine.
 * @property deleteVaccine Deletes a vaccine by its id.
 */
interface VaccinesDAO {
    fun getVaccineById(id: Int): Vaccines?
    fun getVaccineByName(vaccineName: String): Vaccines?
    fun doesVaccineExist(vaccineName: String): Boolean
    fun getVaccineIdByVaccineName(vaccineName: String): Int
    fun getDateAdministeredByVaccineId(id: Int): Date?
    fun getAllVaccines(): Set<Vaccines?>?
    fun insertVaccine(vaccine: Vaccines) : Boolean
    fun updateVaccine(id: Int, vaccine: Vaccines) : Boolean
    fun updateVaccineName(id: Int, vaccineName: String) : Boolean
    fun deleteVaccine(id: Int) : Boolean
}