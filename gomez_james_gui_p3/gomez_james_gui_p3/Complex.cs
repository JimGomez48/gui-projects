using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace gomez_james_gui_p3
{
    struct Complex
    {
        private double real;
        private double imaginary;

        public Complex(double r, double i) {
            real = r;
            imaginary = i;
        }

        public Complex(Complex c) {
            real = c.Real;
            imaginary = c.Imaginary;
        }

        public double Real { get { return real; } }

        public double Imaginary { get { return imaginary; } }

        public static Complex operator +(Complex c1, Complex c2) {
            return new Complex(c1.real + c2.real, c1.imaginary + c2.imaginary);
        }

        public static Complex operator -(Complex c1, Complex c2) {
            return new Complex(c1.real - c2.real, c1.imaginary - c2.imaginary);
        }

        public static Complex operator *(Complex c1, Complex c2) {
            return new Complex(c1.real + c2.real, c1.imaginary + c2.imaginary);
        }

        public static Complex operator /(Complex c1, Complex c2) {
            return new Complex(c1.real + c2.real, c1.imaginary + c2.imaginary);
        }
    }
}
