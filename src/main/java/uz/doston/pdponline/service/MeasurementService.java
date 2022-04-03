package uz.doston.pdponline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.doston.pdponline.dto.ApiResponse;
import uz.doston.pdponline.entity.Measurement;
import uz.doston.pdponline.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

import static uz.doston.pdponline.constants.ResponseConstants.NOT_FOUND;
import static uz.doston.pdponline.constants.ResponseConstants.SUCCESS;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    public ApiResponse add(Measurement measurement) {
        measurementRepository.save(measurement);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse getList() {
        List<Measurement> all = measurementRepository.findAll();
        return new ApiResponse(SUCCESS, true, all);
    }

    public ApiResponse get(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isEmpty())
            return new ApiResponse(NOT_FOUND,false);
        return new ApiResponse(SUCCESS,true,optionalMeasurement.get());
    }

    public ApiResponse delete(Integer id){
        measurementRepository.deleteById(id);
        return new ApiResponse(SUCCESS,true);
    }

    public ApiResponse edit(Integer id, Measurement measurement){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isEmpty())
            return new ApiResponse(NOT_FOUND,false);
        measurement.setId(optionalMeasurement.get().getId());
        measurementRepository.save(measurement);
        return new ApiResponse(SUCCESS,true);
    }

}
