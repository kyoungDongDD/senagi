package com.ssafy.b105.service;

import com.ssafy.b105.dto.ReceiptDto;
import com.ssafy.b105.dto.ReceiptPostDto;
import com.ssafy.b105.dto.SupportLogRequestDto;
import com.ssafy.b105.dto.SupportLogResponseDto;
import com.ssafy.b105.entity.SupportLog;
import com.ssafy.b105.entity.campaign.Campaign;
import com.ssafy.b105.entity.Receipt;
import com.ssafy.b105.repository.CampaignRepository;
import com.ssafy.b105.repository.ReceiptRepository;
import com.ssafy.b105.repository.SupportLogRepository;
import com.ssafy.b105.repository.UserRepository;
import com.ssafy.b105.utils.MD5Generator;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
//@RequiredArgsConstructor
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;
    private final Path rootLocation;


    public ReceiptService(@Value("${uploadPath.path}") String uploadPath) {
        this.rootLocation = Paths.get(uploadPath);
    }


    public ReceiptPostDto store(MultipartFile files, Campaign campaign, String item, Long amount) {
        String originalFilename = files.getOriginalFilename();
        //파일 확장자
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        try {
            String origFilename = files.getOriginalFilename();
            String filename = new MD5Generator(origFilename).toString();
            filename += extension;
            /* 실행되는 위치의 'receiptImage' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\" + rootLocation.toString();
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            //파일 경로
            String filePath = savePath + "\\" + filename;
            files.transferTo(new File(filePath));

            ReceiptDto receiptDto = ReceiptDto.builder()
                .receiptImageUrl(filePath)
                .amount(amount)
                .item(item)
                .campaign(campaign)
                .build();

            Receipt receipt = receiptRepository.save(Receipt.from(receiptDto));
            return ReceiptPostDto.from(receipt);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}


