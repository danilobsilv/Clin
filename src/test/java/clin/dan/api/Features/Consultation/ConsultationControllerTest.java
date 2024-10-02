package clin.dan.api.Features.Consultation;

import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationDataDetailsDTO;
import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationScheduleDataDTO;
import clin.dan.api.Features.DoctorFeatures.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<ConsultationScheduleDataDTO> jacksonTesterScheduleJson;

    @Autowired
    private JacksonTester<ConsultationDataDetailsDTO> jacksonTesterReturnJson;

    @MockBean
    private ConsultationService consultationService;

    @Test
    @DisplayName("Should return HTTP code 400 when the data is invalid")
    @WithMockUser // this endpoint requires authentication, so this is to use a "logged user"
    void scheduleConsultationScenery1() throws Exception{
        var requestResponse = mockMvc
                .perform(post("/consultation/schedule"))
                .andReturn().getResponse();

        assertThat(requestResponse.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return HTTP code 200 when the data is correct")
    @WithMockUser
    void scheduleConsultationScenery2() throws Exception{
        var date = LocalDateTime.now().plusHours(2);
        var specialty = Specialty.CARDIOLOGIA;

        var detailedData = new ConsultationDataDetailsDTO(null, 2l, 5l, date);

        when(consultationService.scheduleConsultation(any())).thenReturn(detailedData);

        var requestResponse = mockMvc.perform(post("/consultation/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTesterScheduleJson.write(
                                new ConsultationScheduleDataDTO(2l, 5l,date, specialty)
                        ).getJson()))
                .andReturn().getResponse();

        assertThat(requestResponse.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJson = jacksonTesterReturnJson.write(
                detailedData
        ).getClass();

        assertThat(requestResponse.getContentAsString()).isEqualTo(expectedJson);
    }
}