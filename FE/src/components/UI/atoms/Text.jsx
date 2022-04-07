function Text(props) {
  return (
    <p className={props.className} color={props.color} onClick={props.func}>
      {props.text}
    </p>
  );
}

export default Text;
