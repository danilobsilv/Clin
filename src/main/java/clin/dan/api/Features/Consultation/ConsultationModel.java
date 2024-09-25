package clin.dan.api.Features.Consultation;

import clin.dan.api.Features.DoctorFeatures.DoctorModel;
import clin.dan.api.Features.PatientFeatures.PatientModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "consultation")
@Entity(name = "Consultation")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ConsultationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorModel doctor_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private PatientModel patient_id;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private CancelMotivation cancel_motivation;

    public void cancelConsultation(CancelMotivation cancelMotivation){
        this.cancel_motivation = cancelMotivation;
    }

}
