import Service from "./views/Service";
import Inquiry from "./views/Inquiry";
import Footer from "./views/OnFooter";
import Intro from "./views/Intro";
import Introduce from "./views/Introduce";
import HowtoUse from "./views/HowtoUse";
import Contact from "./views/Contact";
import Header from "./views/OnHeader";
import withRoot from "./withRoot";

function Index() {
  return (
    <>
      <Header />
      <Intro />
      <Introduce />
      <Service />
      <HowtoUse />
      <Contact />
      <Inquiry />
      <Footer />
    </>
  );
}

export default withRoot(Index);
