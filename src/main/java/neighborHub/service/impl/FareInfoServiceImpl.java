package neighborHub.service.impl;

import neighborHub.model.Entity.FareInfo;
import neighborHub.model.dto.FareInfoDtoReponse;
import neighborHub.repository.FareInfoRepository;
import neighborHub.service.FareInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FareInfoServiceImpl implements FareInfoService{
    @Autowired
    private FareInfoRepository fareInfoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public FareInfoServiceImpl(FareInfoRepository fareInfoRepository, ModelMapper modelMapper)
    {
        this.fareInfoRepository = fareInfoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FareInfo> viewFareInfo() {
        List<FareInfo> fareeInfoList = fareInfoRepository.findAll();
        if (fareeInfoList == null)
            return null;
        return fareeInfoList;
    }

    @Override
    public FareInfoDtoReponse updateFareInfo(FareInfoDtoReponse fareInfoDtoReponse)
    {
        if (fareInfoDtoReponse == null)
            return null;

        FareInfo fareInfo = fareInfoRepository.findById(1L).orElse(null);

        fareInfo.setCommissionRate(fareInfoDtoReponse.getCommissionRate());
        fareInfo.setVatRate(fareInfoDtoReponse.getVatRate());

        fareInfoRepository.save(fareInfo);
        return modelMapper.map(fareInfo, FareInfoDtoReponse.class);
    }
}
