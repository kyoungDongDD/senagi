import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import ButtonBase from "@mui/material/ButtonBase";
import Container from "@mui/material/Container";
import Typography from "../component/Typography";

const ImageBackdrop = styled("div")(({ theme }) => ({
  position: "absolute",
  left: 0,
  right: 0,
  top: 0,
  bottom: 0,
  background: "#000",
  opacity: 0.5,
  transition: theme.transitions.create("opacity"),
}));

const ImageIconButton = styled(ButtonBase)(({ theme }) => ({
  position: "relative",
  display: "block",
  padding: 0,
  borderRadius: 0,
  height: "40vh",
  [theme.breakpoints.down("md")]: {
    width: "100% !important",
    height: 100,
  },
  "&:hover": {
    zIndex: 1,
  },
  "&:hover .imageBackdrop": {
    opacity: 0.15,
  },
  "&:hover .imageMarked": {
    opacity: 0,
  },
  "&:hover .imageTitle": {
    border: "4px solid currentColor",
  },
  "& .imageTitle": {
    position: "relative",
    padding: `${theme.spacing(2)} ${theme.spacing(4)} 14px`,
  },
  "& .imageMarked": {
    height: 3,
    width: 18,
    background: theme.palette.common.white,
    position: "absolute",
    bottom: -2,
    left: "calc(50% - 9px)",
    transition: theme.transitions.create("opacity"),
  },
}));

const images = [
  {
    url: "https://cdn.pixabay.com/photo/2016/10/31/14/55/rottweiler-1785760_960_720.jpg",
    title: "Dog",
    width: "40%",
  },
  {
    url: "https://images.unsplash.com/photo-1531299204812-e6d44d9a185c?auto=format&fit=crop&w=400&q=80",
    title: "Massage",
    width: "20%",
  },
  {
    url: "https://images.unsplash.com/photo-1476480862126-209bfaa8edc8?auto=format&fit=crop&w=400&q=80",
    title: "Hiking",
    width: "40%",
  },
  {
    url: "https://cdn.pixabay.com/photo/2016/08/09/21/24/hedgehog-1581807_960_720.jpg",
    title: "Tour",
    width: "38%",
  },
  {
    url: "https://cdn.pixabay.com/photo/2020/10/01/11/07/hands-5618238_960_720.jpg",
    title: "Donation",
    width: "38%",
  },
  {
    url: "https://cdn.pixabay.com/photo/2018/12/16/16/48/hamster-3878853_960_720.jpg",
    title: "Shopping",
    width: "24%",
  },
  {
    url: "https://cdn.pixabay.com/photo/2021/12/15/04/48/animal-6871771_960_720.jpg",
    title: "RABBIT",
    width: "40%",
  },
  {
    url: "https://cdn.pixabay.com/photo/2021/05/08/17/05/parrot-6238905_960_720.jpg",
    title: "Fitness",
    width: "20%",
  },
  {
    url: "https://cdn.pixabay.com/photo/2015/11/16/22/14/cat-1046544_960_720.jpg",
    title: "CAT",
    width: "40%",
  },
];

export default function Service() {
  return (
    <Container component="section" sx={{ mt: 8, mb: 4 }}>
      <Typography variant="h4" marked="center" align="center" component="h2">
        Service
      </Typography>
      <Box sx={{ mt: 8, display: "flex", flexWrap: "wrap" }}>
        {images.map((image) => (
          <ImageIconButton
            key={image.title}
            style={{
              width: image.width,
            }}
          >
            <Box
              sx={{
                position: "absolute",
                left: 0,
                right: 0,
                top: 0,
                bottom: 0,
                backgroundSize: "cover",
                backgroundPosition: "center 40%",
                backgroundImage: `url(${image.url})`,
              }}
            />
            <ImageBackdrop className="imageBackdrop" />
            <Box
              sx={{
                position: "absolute",
                left: 0,
                right: 0,
                top: 0,
                bottom: 0,
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                color: "common.white",
              }}
            >
              <Typography
                component="h3"
                variant="h6"
                color="inherit"
                className="imageTitle"
              >
                {image.title}
                <div className="imageMarked" />
              </Typography>
            </Box>
          </ImageIconButton>
        ))}
      </Box>
    </Container>
  );
}
