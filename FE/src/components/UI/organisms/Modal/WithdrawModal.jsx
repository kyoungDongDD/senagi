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
  const [fileImage, setFileImage] = useState(''); // 이미지 미리보기
  const [file, setFile] = useState(''); // 이미지 저장
  const [amount, setAmount] = useState(''); // OCR에서 추출한 총액 (서버로 출금요청 보낼 amount)
  const classes = useStyles();
  const campaignId = useParams();

  // 파일 저장
  const saveFileImage = async (e) => {
    await setFileImage(URL.createObjectURL(e.target.files[0]));
    setFile(e.target.files[0]);
    // console.log(file);

    const OCRdata = {
      images: [
        {
          format: 'jpg',
          name: 'demo',
          data: file,
        },
      ],
      requestId: uuidv4(),
      version: 'V2',
      timestamp: Date.now(),
    };

    // console.log(OCRdata);
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
                <input name="imgUpload" type="file" accept="image/*" onChange={saveFileImage} />

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
