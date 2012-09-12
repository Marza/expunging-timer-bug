EJB5119: Expunging timer bug
===================

I experienced a wierd behaviour on GlassFish 3.1.1 where I have a Timer class annotated with @Schedule for cron functionality. This class wraps everything it does within a try-catch block to ensure the timer doesn't crash. But when calling a method on an EJB from the Timer class where the method call is running in a transaction and the method throws any type of RuntimeException the method on the timer will end up in a retry loop and the default configuration for GlassFish 3.1.1 is to retry up to 1 time after 5 seconds and if it fails again the timer will expunge.

You would expect that because everything is wrapped within try-catch that the method on the timer won't fail but it does. Calling a method without transaction scope that throws a RuntimeException won't expunge the timer as expected.

A dirty workaround is to call a method on the EJB that doesn't have transaction scope and that method then calls the method with transaction scope and your timer won't expunge if you really need the transaction scope on the EJB method because the container requires you to have that for what you are doing.