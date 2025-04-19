package spacevisuals.animations.functionanimations.spacetraverseranimations.domaincoloring;

public class MandelbrotSet extends JuliaSet {
	
	public MandelbrotSet(){
		super(input -> getJuliaSetStatus(input, input));
	}
}

    

