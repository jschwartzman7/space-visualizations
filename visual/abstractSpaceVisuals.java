public interface abstractSpaceVisuals {
    void draw(); // axis and labels / other space info
    void update(); // check user movement and update scale accordingly
    void resetDraw(); // set scale to default values
}
