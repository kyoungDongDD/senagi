<<<<<<< HEAD
import { Dialog, DialogActions, DialogContent, DialogTitle, Button, Grid} from '@mui/material';
import Text from '../../atoms/Text';
import BillTable from '../../molecules/BillTable';
import { makeStyles } from '@mui/styles';
import styled from '@emotion/styled';
=======
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
>>>>>>> dev

const useStyles = makeStyles((theme) => {
  return {
    root: {
<<<<<<< HEAD
      "&.MuiButton-root": {
        border: "1px black solid",
      },
      "&.MuiDialogActions": {
        justifyContent: "center"
      }
    },
    withdrawlButton: {
      "&.MuiButton-root": {
        border: "1px white solid",
        "&:hover": {
          border: "1px #f4ba34 solid"
        }
      },
    },
    withdrawlText: {
      "&.MuiDialogTitle-root": {
        margin: "auto",
=======
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
>>>>>>> dev
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
<<<<<<< HEAD
`
=======
`;
>>>>>>> dev

const CancelButton = styled(Button)`
  background-color: white;
  &:hover {
    background-color: black;
    color: white;
  }
  color: black;
  margin: auto;
  width: 200px;
<<<<<<< HEAD
`
=======
`;

>>>>>>> dev
const AttachButton = styled(Button)`
  background-color: white;
  &:hover {
    background-color: black;
    color: white;
  }
  color: black;
  margin: auto;
<<<<<<< HEAD
`

function WithdrawModal(props) {
  const classes = useStyles();

  return (
    <div>
      <Dialog
        open={props.isOpen}
        onClose={props.handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title" className={classes.withdrawlText}>
          <Text className="header1" text="?????? ????????????"/>
        </DialogTitle>
        <DialogContent>
          <Grid container spacing={2}>
            <Grid item xs={6} md={4}>
              <img src="" alt="" />
              <AttachButton className={classes.root} fullWidth>????????? ??????</AttachButton>
            </Grid>
            <Grid item xs={6} md={8}>
              <BillTable />
            </Grid>
          </Grid>
        </DialogContent>
        <DialogActions
          className={classes.root}
          >
          <WithdrawalButton
            className={classes.withdrawlButton}
            onClick={props.handleClose}>
            ????????????
          </WithdrawalButton>
          <CancelButton
            variant="outlined"
            className={classes.root}
            onClick={props.handleClose}>
            ??????
          </CancelButton>
        </DialogActions>
      </Dialog>
    </div>
  );
}


export default WithdrawModal;
=======
`;

// ?????? ????????? ?????? ????????? 90%??? ??????
const StyledDialog = styled(Dialog)`
  min-width: 90%;
  min-height: 90%;
`;

function WithdrawModal(props) {
  let { onClose, isOpen } = props;
  const [fileBase64, setFileBase64] = useState(''); // ?????? Base 64 data
  const [fileFormat, setFileFormat] = useState(''); //?????? format data
  const [fileImage, setFileImage] = useState(''); // ????????? ????????????
  const [file, setFile] = useState(''); // ????????? ??????
  const [amount, setAmount] = useState(''); // OCR?????? ????????? ?????? (????????? ???????????? ?????? amount)
  const [subResults, setSubResults] = useState([]); // ?????? ?????? ?????????
  const classes = useStyles();
  const { campaignId } = useParams(); // API ?????? ?????? campaign id parameter

  // ?????? ????????????
  const withdraw = async () => {
    const formdata = new FormData();
    formdata.append('file', file);
    formdata.append('amount', amount);
    // ????????? ????????? ????????? ????????? ??????
    await PaymentAPI.withdraw(campaignId, formdata)
      .then((response) => {
        // console.log(response);
        handleClose();
        Toast.fire({
          icon: 'success',
          title: '????????? ??????????????????!',
        });
      })
      .catch((error) => {
        console.log(error);
        Toast.fire({
          icon: 'error',
          title: '?????? ?????? ????????? ???????????? ????????? ?????????????????? :(',
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

    // OCR ?????? ?????? ??????
    await PaymentAPI.OCR(OCRdata)
      .then((response) => {
        const result = response.data.images[0].receipt.result;
        const totalPrice = result.totalPrice.price.text.replaceAll('.', '').replaceAll(',', ''); // OCR?????? ,??? .?????? ???????????? ?????? ??????
        setAmount(Number(totalPrice));
        setSubResults(result.subResults[0].items);
      })
      .catch((error) => {
        // console.log(error);
      });
  };

  // fileFormat??? fileBase ??? ?????? ????????? ??? ocrRequest ??????
  useEffect(ocrRequest, [fileFormat && fileBase64]);

  // ????????? -> base64 ?????????
  const handleChangeFile = async (event) => {
    let reader = new FileReader();
    setFileImage(URL.createObjectURL(event.target.files[0])); // ????????? ????????????

    // 2. ?????? ?????? ??? base64 encoding
    reader.onloadend = () => {
      const base64 = reader.result;
      if (base64) {
        let dataType = reader.result.split(';base64,')[0];
        setFileFormat(dataType.split('/')[1]); // ????????? ?????? ??????
        let base64result = base64.split(',')[1]; // ???????????? ????????? "," ?????? ??????
        setFileBase64(base64result);
      }
    };

    if (event.target.files[0]) {
      reader.readAsDataURL(event.target.files[0]); // 1. ?????? ????????? ??????
      setFile(event.target.files[0]); // ????????? ?????? (form-data??? ?????? ???)
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
        <Text className="header1" text="?????? ????????????" />
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
                    ????????? ??????
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
          ????????????
        </WithdrawalButton>
        <CancelButton variant="outlined" className={classes.root} onClick={handleClose}>
          ??????
        </CancelButton>
      </DialogActions>
    </StyledDialog>
  );
}

export default WithdrawModal;
>>>>>>> dev
