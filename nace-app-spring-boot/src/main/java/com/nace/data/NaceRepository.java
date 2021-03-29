package com.nace.data;

import org.springframework.data.repository.CrudRepository;

public interface NaceRepository extends CrudRepository<NaceEntity,Long> {
    NaceEntity findByOrderId(int orderId);
}
