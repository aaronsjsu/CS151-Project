/*
Copyright © 2018 Kevin Tyrrell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package model.receipt;

/**
 * Defines different types of receipt formatters.
 * Utilizes the strategy design pattern.
 *
 * @see Receipt
 * @since 1.0
 */
public enum ReceiptFormatter
{
    SIMPLE 
    {
        @Override public String formatReceipt(final Receipt receipt)
        {
            return null;
        }
    },
    COMPREHENSIVE
    {
        @Override public String formatReceipt(final Receipt receipt)
        {
            return null;
        }
    };

    /**
     * Formats a specified receipt for viewing.
     * 
     * @param receipt Receipt to format.
     * @return String of the formatted receipt.
     */
    public abstract String formatReceipt(final Receipt receipt);
}
