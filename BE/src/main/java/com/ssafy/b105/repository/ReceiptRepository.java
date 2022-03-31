package com.ssafy.b105.repository;

import com.ssafy.b105.entity.campaign.Campaign;
import com.ssafy.b105.entity.Receipt;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {


}
