package neighborHub.service;

import neighborHub.model.Entity.FareInfo;
import neighborHub.model.dto.FareInfoDtoReponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FareInfoService {
    List<FareInfo> viewFareInfo();

    FareInfoDtoReponse updateFareInfo(FareInfoDtoReponse fareInfoDtoReponse);

    FareInfo getFareInfo();
}
