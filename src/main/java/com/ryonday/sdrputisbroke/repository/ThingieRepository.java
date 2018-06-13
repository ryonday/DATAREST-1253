package com.ryonday.sdrputisbroke.repository;

import com.ryonday.sdrputisbroke.domain.Thingie;
import com.ryonday.sdrputisbroke.domain.ThingieId;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ThingieRepository extends CrudRepository<Thingie, ThingieId> {
}
