package edu.java.domain.model;

import java.net.URI;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LinkDTO {
    private URI uri;
    private Long tgChatId;
    private Long linkId;
    private String hash;
    private OffsetDateTime createdAt;
    private OffsetDateTime lastUpdate;
}