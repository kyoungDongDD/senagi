import { Button } from '@mui/material';
import Text from '../atoms/Text';
function MoreButton() {
  return (
    <>
      <Button size="large" color="warning">
        <Text text="더보기 >" />
      </Button>
    </>
  );
}

export default MoreButton;
