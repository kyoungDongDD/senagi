package com.ssafy.b105.controller;


import com.ssafy.b105.dto.ReceiptListDto;
import com.ssafy.b105.dto.SupportLogRequestDto;
import com.ssafy.b105.entity.campaign.Campaign;
import com.ssafy.b105.repository.ReceiptRepository;
import com.ssafy.b105.service.CampaignService;
import com.ssafy.b105.service.ReceiptService;
import com.ssafy.b105.service.SupportLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ReceiptController {

    @Autowired
    private ReceiptRepository receiptRepository;
    private final CampaignService campaignService;
    private final ReceiptService receiptService;
    private final SupportLogService supportLogService;
    //캠페인 출금 영수증 조회
    @GetMapping("/api/payment/withdrawal/{id}")
    public ResponseEntity<ReceiptListDto> CampaignReceipt(@PathVariable("id") Campaign campaign) {
        return ResponseEntity.ok(campaignService.receiptList(campaign));
    }

    @PostMapping("/api/payment/receipt/{id}")
    public ResponseEntity<?> ReceiptAttach(@PathVariable("id") Campaign campaign,
        @RequestParam("file") MultipartFile files, @RequestParam("item") String item,
        @RequestParam("amount") Long amount) {
        return ResponseEntity.ok(receiptService.store(files, campaign, item, amount));
    }

    @PostMapping("/api/payment/donate")
    public ResponseEntity<?> donate(@RequestBody SupportLogRequestDto supportLogDto) {
        return ResponseEntity.ok(supportLogService.donation(supportLogDto));
    }
}