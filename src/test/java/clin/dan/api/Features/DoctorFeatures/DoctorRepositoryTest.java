package clin.dan.api.Features.DoctorFeatures;

import clin.dan.api.AddressDataDTO;
import clin.dan.api.Features.Consultation.ConsultationModel;
import clin.dan.api.Features.DoctorFeatures.DoctorDTOs.InsertDoctorDTO;
import clin.dan.api.Features.PatientFeatures.PatientDTO.PatientRegistrationDTO;
import clin.dan.api.Features.PatientFeatures.PatientModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

// too many sceneries
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private AddressDataDTO setAddress(String street, String neighbourhood, String cep, String city, String uf, String number, String complement){
        return new AddressDataDTO(street, neighbourhood, cep, city, uf, number, complement);
    }

    private DoctorModel registerDoctor(String name, String email, String phoneNumber, String crm, Specialty specialty, AddressDataDTO address){
        var doctor = new DoctorModel(new InsertDoctorDTO(name, email, phoneNumber, crm, specialty, address));

        testEntityManager.persist(doctor);

        return doctor;
    }

    private PatientModel registerPatient(String name, String email, String phoneNumber, String cpf, AddressDataDTO address){
        var patient = new PatientModel(new PatientRegistrationDTO(name,email, phoneNumber, cpf, address));

        testEntityManager.persist(patient);

        return patient;
    }

    private void registerConsultation(DoctorModel doctor, PatientModel patient, LocalDateTime date){
        testEntityManager.persist(new ConsultationModel(null, doctor, patient, date, null));
    }


    @Test
    @DisplayName("It should return null when the registered doctor isn't available at the date.")
    void chooseRandomFreeDoctorInTheDateFirstScenery() {
        var nextMondayAtTen = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        var doctor = registerDoctor("doctor test 1", "doctortest1@gmail.com", "92999999999", "123445", Specialty.CARDIOLOGIA, setAddress("rua1", "bairro1", "11111-111", "manaus", "AM", "1", "test complement 1"));
        var patient = registerPatient("patient test 1", "patienttest1@gmail.com", "92999999999", "123456", setAddress("rua1", "bairro1", "11111-111", "manaus", "AM", "1", "test complement 1"));
        registerConsultation(doctor, patient, nextMondayAtTen);

        var freeDoctor = doctorRepository.chooseRandomFreeDoctorInTheDate(Specialty.CARDIOLOGIA, nextMondayAtTen);
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Return the doctor when the he is available at the date.")
    void chooseRandomFreeDoctorInTheDateSecondScenery() {
        var nextMondayAtTen = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        var doctor = registerDoctor("doctor test 1", "doctortest1@gmail.com", "92999999999", "123445", Specialty.CARDIOLOGIA, setAddress("rua1", "bairro1", "11111-111", "manaus", "AM", "1", "test complement 1"));

        var freeDoctor = doctorRepository.chooseRandomFreeDoctorInTheDate(Specialty.CARDIOLOGIA, nextMondayAtTen);
        assertThat(freeDoctor).isEqualTo(doctor);
    }
}