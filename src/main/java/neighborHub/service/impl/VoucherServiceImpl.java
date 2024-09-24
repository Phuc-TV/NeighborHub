package neighborHub.service.impl;

import neighborHub.model.Entity.Voucher;
import neighborHub.model.dto.VoucherDtoRequest;
import neighborHub.model.dto.VoucherDtoResponse;
import neighborHub.repository.VoucherRepository;
import neighborHub.service.VoucherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private ModelMapper modelMapper;

    public VoucherServiceImpl(VoucherRepository voucherRepository, ModelMapper modelMapper)
    {
        this.voucherRepository = voucherRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<VoucherDtoResponse> viewListAllVoucher() {
        List<Voucher> voucherList = voucherRepository.findAll();
        if (voucherList == null)
            return null;
        List<VoucherDtoResponse> vouchers = voucherList.stream()
                .map(voucherList1 -> modelMapper.map(voucherList1, VoucherDtoResponse.class)).toList();
        return vouchers;
    }

    @Override
    public VoucherDtoResponse createVoucher(VoucherDtoRequest voucher) {
        Voucher voucher1 = modelMapper.map(voucher, Voucher.class);
        voucherRepository.save(voucher1);

        return modelMapper.map(voucher1, VoucherDtoResponse.class);
    }

    @Override
    public VoucherDtoResponse updateVoucher(VoucherDtoRequest voucher, int id)
    {
        Voucher voucherById = voucherRepository.findById(id).orElse(null);

        if (voucherById == null)
            return null;

        voucherById.setCode(voucher.getCode());
        voucherById.setDescription(voucher.getDescription());
        voucherById.setDiscount(voucher.getDiscount());
        voucherById.setExpiryDate(voucher.getExpiryDate());

        voucherRepository.save(voucherById);
        return modelMapper.map(voucherById, VoucherDtoResponse.class);
    }

    @Override
    public Boolean deleteVoucher(int id)
    {
        Voucher voucher = voucherRepository.findById(id).orElse(null);
        if (voucher == null)
            return false;

        voucher.setStatus(true);
        voucherRepository.save(voucher);
        return true;
    }
}
