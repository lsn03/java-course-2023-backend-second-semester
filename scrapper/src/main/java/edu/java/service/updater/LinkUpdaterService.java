package edu.java.service.updater;

import edu.java.model.scrapper.dto.request.LinkUpdateRequest;
import java.util.List;

public interface LinkUpdaterService {
    List<LinkUpdateRequest> update();
}
