package neighborHub.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import neighborHub.model.dto.UserDto;
import neighborHub.model.dto.VoucherDtoRequest;
import neighborHub.model.dto.VoucherDtoResponse;
import neighborHub.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/voucher")
public class VoucherController {
    private VoucherService voucherService;

    @Autowired
    public VoucherController(VoucherService voucherService)
    {
        this.voucherService = voucherService;
    }

    @GetMapping("/viewAllVoucher")
    public ResponseEntity<List<VoucherDtoResponse>> viewListAllVoucher(){
        try {
            List<VoucherDtoResponse> allVoucherList = voucherService.viewListAllVoucher();
            if(allVoucherList != null){
                return new ResponseEntity<>(allVoucherList, HttpStatus.OK);
            }else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PostMapping("/CreateVoucher")
    public ResponseEntity<VoucherDtoResponse>  createVoucher (@RequestBody VoucherDtoRequest voucherDtoRequest)
    {
        VoucherDtoResponse createVoucer = voucherService.createVoucher(voucherDtoRequest);
        if (createVoucer != null)
        {
            return new ResponseEntity<>(createVoucer,HttpStatus.ACCEPTED);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PutMapping("/UpdateVoucher")
    public ResponseEntity<VoucherDtoResponse> editVoucher(@PathVariable int id, @RequestBody VoucherDtoRequest voucherDtoRequest){
        VoucherDtoResponse editVoucher = voucherService.updateVoucher(voucherDtoRequest, id);
        if(editVoucher != null){
            return new ResponseEntity<>(editVoucher,HttpStatus.ACCEPTED);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @SecurityRequirement(name = "Bear Authentication")
    @DeleteMapping("/DeleteVoucher/{id}")
    public ResponseEntity<String> deleteVoucher(@PathVariable int id) {
        boolean deletionResult = voucherService.deleteVoucher(id);

        if (deletionResult) {
            return new ResponseEntity<>("Voucher deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to delete Voucher. Please check the VoucherId.", HttpStatus.BAD_REQUEST);
        }
    }
}
