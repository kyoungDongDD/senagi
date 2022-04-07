import { useEffect, useRef, useState } from 'react';
import {
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Button,
  Grid,
  Input,
} from '@mui/material';
import Text from '../../atoms/Text';
import BillTable from '../../molecules/BillTable';
import { makeStyles } from '@mui/styles';
import styled from '@emotion/styled';
import PaymentAPI from '../../../../api/paymentAPI';
import { useParams } from 'react-router-dom';
import { Preview } from '@mui/icons-material';
import { v4 as uuidv4 } from 'uuid';

const useStyles = makeStyles((theme) => {
  return {
    root: {
      '&.MuiButton-root': {
        border: '1px black solid',
      },
      '&.MuiDialogActions': {
        justifyContent: 'center',
      },
    },
    withdrawlButton: {
      '&.MuiButton-root': {
        border: '1px white solid',
        '&:hover': {
          border: '1px #f4ba34 solid',
        },
      },
    },
    withdrawlText: {
      '&.MuiDialogTitle-root': {
        margin: 'auto',
      },
    },
  };
});

const WithdrawalButton = styled(Button)`
  background-color: #f4ba34;
  &:hover {
    background-color: white;
    color: #f4ba34;
  }
  color: white;
  margin: auto;
  width: 200px;
`;

const CancelButton = styled(Button)`
  background-color: white;
  &:hover {
    background-color: black;
    color: white;
  }
  color: black;
  margin: auto;
  width: 200px;
`;

const AttachButton = styled(Button)`
  background-color: white;
  &:hover {
    background-color: black;
    color: white;
  }
  color: black;
  margin: auto;
`;

// 모달 사이즈 전체 화면의 90%로 고정
const StyledDialog = styled(Dialog)`
  min-width: 90%;
  min-height: 90%;
`;

function WithdrawModal(props) {
  let { onClose, isOpen } = props;
  const [fileBase64, setfileBase64] = useState(''); // 파일 Base 64
  // const [imgFile, setImgFile] = useState(null); //파일
  const [fileImage, setFileImage] = useState(''); // 이미지 미리보기
  const [file, setFile] = useState(''); // 이미지 저장
  const [amount, setAmount] = useState(''); // OCR에서 추출한 총액 (서버로 출금요청 보낼 amount)
  const classes = useStyles();
  const campaignId = useParams(); // API 요청 보낼 campaign id parameter

  // OCR
  const ocrRequest = async (event) => {
    const OCRdata = {
      images: [
        {
          format: 'jpg',
          name: 'demo',
          data: fileBase64,
        },
      ],
      requestId: uuidv4(),
      version: 'V2',
      timestamp: Date.now(),
    };

    // OCR 분석 결과 출력
    await PaymentAPI.OCR(OCRdata)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  // 이미지 -> base64 인코딩
  const handleChangeFile = async (event) => {
    let reader = new FileReader();
    setFileImage(URL.createObjectURL(event.target.files[0])); // 이미지 미리보기
    setFile(event.target.files[0]); // 이미지 저장 (form-data로 보낼 것)

    console.log(file);
    // 읽기 완료 후 base64 encoding
    reader.onload = () => {
      console.log(reader);
      const base64 = reader.result;
      if (base64) {
        const temp = base64.toString();
        console.log(temp);
        setfileBase64(temp.substring(',')); // 불필요한 데이터 "," 기준 삭제
        console.log(fileBase64);
      }
    };

    ocrRequest();
  };

  // 파일 저장
  const saveFileImage = async (e) => {
    setFileImage(URL.createObjectURL(e.target.files[0])); // 이미지 미리보기
    setFile(e.target.files[0]); // 이미지 저장 (form-data로 보낼 것)
    // console.log(file);

    // 이미지 파일 Base64 Encode
    handleChangeFile();
    console.log(fileBase64);

    const OCRdata = {
      images: [
        {
          format: 'jpg',
          name: 'demo',
          data: fileBase64,
        },
      ],
      requestId: uuidv4(),
      version: 'V2',
      timestamp: Date.now(),
    };

    console.log(OCRdata);
    // OCR 분석
    await PaymentAPI.OCR(OCRdata)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  // 파일 삭제
  const deleteFileImage = () => {
    URL.revokeObjectURL(fileImage);
    setFileImage('');
  };

  // 파일 전송하기
  const withdraw = async () => {
    // const formdata = new FormData();
    // formdata.append('file', file);
    // formdata.append('amount', amount);
    // console.log(file);
    // // 서버로 파일 전송
    // await PaymentAPI.withdraw(campaignId, formdata)
    //   .then((response) => {
    //     console.log(response);
    //   })
    //   .catch((error) => {
    //     console.log(error);
    //   });
  };

  const handleClose = () => {
    onClose(false);
  };

  return (
    <StyledDialog
      open={props.isOpen}
      onClose={handleClose}
      aria-labelledby="alert-dialog-title"
      aria-describedby="alert-dialog-description"
    >
      <DialogTitle id="alert-dialog-title" className={classes.withdrawlText}>
        <Text className="header1" text="출금 신청하기" />
      </DialogTitle>
      <DialogContent>
        <Grid container spacing={2}>
          <Grid item xs={6} md={4}>
            <div>
              {fileImage && (
                <img alt="sample" src={fileImage} style={{ margin: 'auto', width: '100%' }} />
              )}
              <div
                style={{
                  alignItems: 'center',
                  justifyContent: 'center',
                }}
              >
                {/* <input type="file" name="imgFile" id="imgFile" onChange={handleChangeFile} /> */}
                <input name="imgUpload" type="file" accept="image/*" onChange={handleChangeFile} />

                <button
                  style={{
                    backgroundColor: 'gray',
                    color: 'white',
                    width: '55px',
                    height: '40px',
                    cursor: 'pointer',
                  }}
                  onClick={() => deleteFileImage()}
                >
                  삭제
                </button>
              </div>
            </div>
            {/* <div className="img__box"></div> */}
            {/* <div style={{ backgroundColor: '#efefef', width: '100%', height: '100%' }}></div> */}
            {/* 파일선택 _____ + hover 지우는 방법? */}
            {/* <label htmlFor="contained-button-file"> */}
            {/* <Input */}
            {/* accept="image/*" */}
            {/* id="contained-button-file" */}
            {/* multiple */}
            {/* type="file" */}
            {/* onChange={onInputImage} */}
            {/* /> */}
            {/* <AttachButton className={classes.root} fullWidth component="span"> */}
            {/* 영수증 첨부 */}
            {/* </AttachButton> */}
            {/* </label> */}
          </Grid>
          <Grid item xs={6} md={8}>
            <BillTable />
          </Grid>
        </Grid>
      </DialogContent>
      <DialogActions className={classes.root}>
        <WithdrawalButton className={classes.withdrawlButton} onClick={withdraw}>
          출금신청
        </WithdrawalButton>
        <CancelButton variant="outlined" className={classes.root} onClick={handleClose}>
          취소
        </CancelButton>
      </DialogActions>
    </StyledDialog>
  );
}

export default WithdrawModal;
