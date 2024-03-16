package edu.java.domain.model;

import edu.java.model.stack_over_flow.dto.QuestionAnswerDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StackOverFlowAnswerDTO {
    private Long linkId;
    private Long answerId;
    private String userName;
    private Boolean isAccepted;
    private OffsetDateTime creationDate;
    private OffsetDateTime lastActivityDate;
    private OffsetDateTime lastEditDate;

    public static StackOverFlowAnswerDTO create(QuestionAnswerDTOResponse questionAnswerDTOResponse){
        StackOverFlowAnswerDTO stackOverFlowAnswerDTO = new StackOverFlowAnswerDTO();

        stackOverFlowAnswerDTO.setAnswerId((long) questionAnswerDTOResponse.getAnswerId());
        stackOverFlowAnswerDTO.setUserName(questionAnswerDTOResponse.getOwner().getName());
        stackOverFlowAnswerDTO.setIsAccepted(questionAnswerDTOResponse.isAccepted());
        stackOverFlowAnswerDTO.setCreationDate(questionAnswerDTOResponse.getCreationDate());
        stackOverFlowAnswerDTO.setLastActivityDate(questionAnswerDTOResponse.getLastActivityDate());
        stackOverFlowAnswerDTO.setLastEditDate(questionAnswerDTOResponse.getLastEditDate());

        return stackOverFlowAnswerDTO;
    }
}
