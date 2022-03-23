import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'
from keras.models import load_model
from tkinter import *
import tkinter as tk
import win32gui
from PIL import ImageGrab, Image, ImageOps
import numpy as np
import matplotlib.pyplot as plt

model = load_model('myModel.h5')


def predict_digit(img):
    img = img.resize((28, 28))
    img = ImageOps.invert(img)
    img = img.convert('L')
    img = np.array(img)

    img1 = img.reshape(-1, 28, 28, 1)
    img1 = img1 / 255

    res = model.predict(img1)
    print(res)
    res = list(res)
    print(res)
    return np.argmax(res)


class App(tk.Tk):
    def __init__(self):
        tk.Tk.__init__(self)

        self.x = self.y = 0

        self.canvas = tk.Canvas(self, width=300, height=300, bg='white', cursor='cross')
        self.label = tk.Label(self, text="Думаю...", font=("Helvetica", 48))
        self.classify_btn = tk.Button(self, text="Распознать", command=self.classify_handwritting)
        self.button_clear = tk.Button(self, text="Clear", command=self.clear_all)

        self.canvas.grid(row=0, column=0, pady=2, sticky=W)
        self.label.grid(row=0, column=1, pady=2, padx=2)
        self.classify_btn.grid(row=1, column=1, pady=2, padx=2)
        self.button_clear.grid(row=1, column=0, pady=2)

        self.canvas.bind("<B1-Motion>", self.draw_lines)

    def classify_handwritting(self):
        HWND = self.canvas.winfo_id()
        rect = win32gui.GetWindowRect(HWND)
        im = ImageGrab.grab(rect)
        digit = predict_digit(im)
        self.label.configure(text=str(digit))

    def draw_lines(self, event):
        self.x = event.x
        self.y = event.y
        r = 5
        self.canvas.create_oval(self.x - r, self.y - r, self.x + r, self.y + r, fill='black')

    def clear_all(self):
        self.canvas.delete("all")

app = App()
mainloop()