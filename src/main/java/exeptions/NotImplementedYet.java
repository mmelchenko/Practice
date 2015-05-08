package exeptions;

import constants.StringConstants;

/**
 * Created by odudak on 08.05.15.
 */
public class NotImplementedYet extends RuntimeException {
    public NotImplementedYet() {
        super(StringConstants.NOT_IMPLEMENTED_YET);
    }
}
