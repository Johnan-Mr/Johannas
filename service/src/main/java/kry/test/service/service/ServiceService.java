package kry.test.service.service;

import kry.test.service.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    public List<kry.test.service.model.Service> listAll() {
        return serviceRepository.findAll();
    }

    public kry.test.service.model.Service get(Integer id) {
        return serviceRepository.findById(id).get();
    }

    public List<kry.test.service.model.Service> pollServices() { return serviceRepository.findAll();
    }

    public void saveService(kry.test.service.model.Service service) { serviceRepository.save(service);
    }
}
