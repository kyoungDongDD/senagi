import { useEffect, useState } from 'react';
import { Dialog, DialogActions, DialogContent, DialogTitle, Button, Grid } from '@mui/material';
import Text from '../../atoms/Text';
import Toast from '../../atoms/SweetAlert';
import BillTable from '../../molecules/BillTable';
import { makeStyles } from '@mui/styles';
import styled from '@emotion/styled';
import PaymentAPI from '../../../../api/paymentAPI';
import { useParams } from 'react-router-dom';
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
  const [fileBase64, setFileBase64] = useState(''); // 파일 Base 64 data
  const [fileFormat, setFileFormat] = useState(''); //파일 format data
  const [fileImage, setFileImage] = useState(''); // 이미지 미리보기
  const [file, setFile] = useState(''); // 이미지 저장
  const [amount, setAmount] = useState(''); // OCR에서 추출한 총액 (서버로 출금요청 보낼 amount)
  const [subResults, setSubResults] = useState([]); // 상세 항목 데이터
  const classes = useStyles();
  const { campaignId } = useParams(); // API 요청 보낼 campaign id parameter

  // 파일 전송하기
  const withdraw = async () => {
    const formdata = new FormData();
    formdata.append('file', file);
    formdata.append('amount', amount);
    // 서버로 영수증 파일과 총액만 전송
    await PaymentAPI.withdraw(campaignId, formdata)
      .then((response) => {
        // console.log(response);
        handleClose();
        Toast.fire({
          icon: 'success',
          title: '출금에 성공했습니다!',
        });
      })
      .catch((error) => {
        console.log(error);
        Toast.fire({
          icon: 'error',
          title: '출금 도중 에러가 발생하여 출금에 실패했습니다 :(',
        });
      });
  };

  // OCR
  const ocrRequest = async (event) => {
    const OCRdata = {
      images: [
        {
          format: fileFormat,
          name: Date.now().toString(),
          data: fileBase64,
        },
      ],
      requestId: uuidv4(),
      version: 'V2',
      timestamp: Date.now().toString(),
    };

    // OCR 분석 결과 출력
    await PaymentAPI.OCR(OCRdata)
      .then((response) => {
        const result = response.data.images[0].receipt.result;
        const totalPrice = result.totalPrice.price.text.replaceAll('.', '').replaceAll(',', ''); // OCR에서 ,을 .으로 인식하는 문제 해결
        setAmount(Number(totalPrice));
        setSubResults(result.subResults[0].items);
      })
      .catch((error) => {
        // console.log(error);
      });
  };

  // fileFormat과 fileBase 에 값이 갱신된 후 ocrRequest 요청
  useEffect(ocrRequest, [fileFormat && fileBase64]);

  // 이미지 -> base64 인코딩
  const handleChangeFile = async (event) => {
    let reader = new FileReader();
    setFileImage(URL.createObjectURL(event.target.files[0])); // 이미지 미리보기

    // 2. 읽기 완료 후 base64 encoding
    reader.onloadend = () => {
      const base64 = reader.result;
      if (base64) {
        let dataType = reader.result.split(';base64,')[0];
        setFileFormat(dataType.split('/')[1]); // 데이터 타입 저장
        let base64result = base64.split(',')[1]; // 불필요한 데이터 "," 기준 삭제
        setFileBase64(base64result);
      }
    };

    if (event.target.files[0]) {
      reader.readAsDataURL(event.target.files[0]); // 1. 파일 읽어서 저장
      setFile(event.target.files[0]); // 이미지 저장 (form-data로 보낼 것)
    }
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
      sx={{ minWidth: 9 / 10, minHeight: 9 / 10 }}
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
                <label>
                  <input
                    name="imgUpload"
                    type="file"
                    accept="image/*"
                    onChange={handleChangeFile}
                    style={{ display: 'none' }}
                  />
                  <AttachButton className={classes.root} fullWidth component="span">
                    영수증 첨부
                  </AttachButton>
                </label>
              </div>
            </div>
          </Grid>
          <Grid item xs={6} md={8}>
            <BillTable subResults={subResults} totalAmount={amount} />
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
