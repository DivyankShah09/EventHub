import React from "react";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import { Bar } from "react-chartjs-2";

const getRandomColor = () => {
  const r = Math.floor(Math.random() * 256);
  const g = Math.floor(Math.random() * 256);
  const b = Math.floor(Math.random() * 256);
  return `rgba(${r}, ${g}, ${b}, 0.5)`;
};

const BarChart = ({ dataValues, labels, title }) => {
  ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip);
  const options = {
    responsive: true,
    plugins: {
      title: {
        display: true,
        text: title,
      },
    },
    scales: {
      x: {
        grid: {
          display: false,
        },
      },
      y: {
        grid: {
          display: false,
        },
      },
    },
  };
  const data = {
    labels,
    datasets: [
      {
        data: dataValues,
        backgroundColor: "#83c5be",
      },
    ],
  };
  return <Bar className="w-full" options={options} data={data} />;
};

export default BarChart;
