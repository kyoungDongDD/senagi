function Text(props) {
  return (
    <p className={props.className} color={props.color}>
      {props.text}
    </p>
  );
}

export default Text;
