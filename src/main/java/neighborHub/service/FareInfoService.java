package neighborHub.service;

import neighborHub.model.Entity.FareInfo;
import neighborHub.model.dto.FareInfoDtoReponse;

import java.util.List;

public interface FareInfoService {
    List<FareInfo> viewFareInfo();

    FareInfoDtoReponse updateFareInfo(FareInfoDtoReponse fareInfoDtoReponse);
}
