import { Button, Link } from '@mui/material';
import Text from '../atoms/Text';
function MoreButton() {
  return (
    <>
      <Button size="large">
        <Link href="/campaign" underline="none" color="black">
          <Text text="더보기 >" />
        </Link>
      </Button>
    </>
  );
}

export default MoreButton;
