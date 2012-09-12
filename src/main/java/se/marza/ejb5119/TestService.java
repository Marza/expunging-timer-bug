package se.marza.ejb5119;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Test service.
 *
 * @author Tony Marjakangas | tony@fareoffice.com
 */
@Stateless
public class TestService
{
	/**
	 *
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void throwsRuntimeExceptionWithinTransaction()
	{
		throw new RuntimeException();
	}

	/**
	 *
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void throwsRuntimeExceptionWithoutTransaction()
	{
		throw new RuntimeException();
	}
}
