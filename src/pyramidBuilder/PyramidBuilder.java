package pyramidBuilder;

import java.util.List;

public interface PyramidBuilder {
    List<List<Integer>> build(int[] arr) throws CannotBuildPyramidException;
}
