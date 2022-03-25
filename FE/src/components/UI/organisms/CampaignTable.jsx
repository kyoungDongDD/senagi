
import UserButton from "../molecules/UserButton";
import '../../../styles/CampaignTable.css';

function CampaignTable() {
  return (
    <div>
      <p>캠페인 및 보호소 정보</p>
      <table className="myTable headerV">
        <tr>
          <td>사업기간</td>
          <td>2022.03.05 ~ 2022.12.31</td>
        </tr>
        <tr>
          <td>보호소</td>
          <td>마석유기견보호소</td>
        </tr>
        <tr>
          <td>태그</td>
          <td><button>태그버튼 만들기!</button></td>
        </tr>
      </table>
      <UserButton
        type="submit" 
        fullWidth variant="contained" 
        text="캠페인 기부하기" 
        size="large" />
    </div>
  );
}

export default CampaignTable;
