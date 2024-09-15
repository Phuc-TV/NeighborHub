package neighborHub.controller;

import neighborHub.model.Entity.FareInfo;
import neighborHub.model.dto.FareInfoDtoReponse;
import neighborHub.model.dto.VoucherDtoRequest;
import neighborHub.model.dto.VoucherDtoResponse;
import neighborHub.service.FareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fareinfo")
public class FareInfoController {
    private FareInfoService fareInfoService;

    public FareInfoController(FareInfoService fareInfoService)
    {
        this.fareInfoService = fareInfoService;
    }

    @GetMapping("/viewAllFareInfo")
    public ResponseEntity<List<FareInfo>> viewListAllVoucher(){
        try {
            List<FareInfo> allFareInfo = fareInfoService.viewFareInfo();
            if(allFareInfo != null){
                return new ResponseEntity<>(allFareInfo, HttpStatus.OK);
            }else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/UpdateFareInfo")
    public ResponseEntity<FareInfoDtoReponse> editInfoStudent(@RequestBody FareInfoDtoReponse fareInfoDtoReponse){
        FareInfoDtoReponse editVoucher = fareInfoService.updateFareInfo(fareInfoDtoReponse);
        if(editVoucher != null){
            return new ResponseEntity<>(editVoucher,HttpStatus.ACCEPTED);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
