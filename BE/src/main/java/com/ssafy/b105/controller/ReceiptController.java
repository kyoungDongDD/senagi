package com.ssafy.b105.controller;


import com.ssafy.b105.auth.Jwt.domain.JwtAuthentication;
import com.ssafy.b105.dto.UserDonateDto;
import com.ssafy.b105.dto.ReceiptListDto;
import com.ssafy.b105.dto.SupportLogRequestDto;
import com.ssafy.b105.entity.campaign.Campaign;
import com.ssafy.b105.entity.user.User;
import com.ssafy.b105.repository.ReceiptRepository;
import com.ssafy.b105.repository.UserRepository;
import com.ssafy.b105.service.CampaignService;
import com.ssafy.b105.service.ReceiptService;
import com.ssafy.b105.service.SupportLogService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final UserRepository userRepository;
    private final SupportLogService supportLogService;
    //캠페인 출금 영수증 조회
    @GetMapping("/api/payment/withdrawal/{id}")
    public ResponseEntity<ReceiptListDto> CampaignReceipt(@PathVariable("id") Campaign campaign) {
        return ResponseEntity.ok(campaignService.receiptList(campaign));
    }

    //출금하기(영수증 첨부)
    @PostMapping("/api/payment/receipt/{id}")
    public ResponseEntity<?> WithdrawalAndReceiptAttach(@PathVariable("id") Campaign campaign,
        @RequestParam("file") MultipartFile files,@RequestParam("amount") Long amount) {
        return ResponseEntity.ok(receiptService.withdrawal(files, campaign, amount));
    }
    //후원하기
    @PostMapping("/api/payment/donate")
    public ResponseEntity<?> Donate(@RequestBody SupportLogRequestDto supportLogRequestDto,@AuthenticationPrincipal JwtAuthentication jwtAuthentication) {
        return ResponseEntity.ok(supportLogService.donation(supportLogRequestDto,jwtAuthentication.getId()));
    }
    //내 기부 내역
    @GetMapping("/api/payment/my/donation")
    public ResponseEntity<List<UserDonateDto>> UserDonations(@AuthenticationPrincipal JwtAuthentication jwtAuthentication)  {
        User user = userRepository.findById(jwtAuthentication.getId()).get();
        return ResponseEntity.ok(userRepository.userDonate(user));
    }
}